package com.gucardev.springjpainheritanceentity.factory.post.create;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.gucardev.springjpainheritanceentity.enumeration.PostType;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PostFactoryProducerTest {
  private PostFactoryProducer producer;

  @Mock private ImagePostFactory imagePostFactory;

  @Mock private PollPostFactory pollPostFactory;

  @Mock private TextPostFactory textPostFactory;

  @BeforeEach
  public void setup() {

    Map<String, PostFactory> factoryMap = new HashMap<>();
    factoryMap.put(PostType.IMAGE.factoryBeanName(), imagePostFactory);
    factoryMap.put(PostType.POLL.factoryBeanName(), pollPostFactory);
    factoryMap.put(PostType.TEXT.factoryBeanName(), textPostFactory);

    producer = new PostFactoryProducer(factoryMap);
  }

  private static Stream<Arguments> provideTestScenarios() {
    return Stream.of(
        Arguments.of(PostType.IMAGE, ImagePostFactory.class),
        Arguments.of(PostType.POLL, PollPostFactory.class),
        Arguments.of(PostType.TEXT, TextPostFactory.class));
  }

  @ParameterizedTest
  @MethodSource("provideTestScenarios")
  void testGetFactoryByType_ValidTypes(
      PostType type, Class<? extends PostFactory> expectedFactoryClass) {
    PostFactory result = producer.getFactoryByType(type);
    assertTrue(expectedFactoryClass.isInstance(result));
  }

  @Test
  void testGetFactoryByType_WithMissingPostFactory() {
    Map mockFactoryMap = mock(Map.class);
    when(mockFactoryMap.get(PostType.TEXT.factoryBeanName())).thenReturn(null);
    PostFactoryProducer mockProducer = new PostFactoryProducer(mockFactoryMap);
    Exception exception =
        assertThrows(
            IllegalArgumentException.class, () -> mockProducer.getFactoryByType(PostType.TEXT));
    assertEquals("Invalid PostType provided", exception.getMessage());
  }
}
